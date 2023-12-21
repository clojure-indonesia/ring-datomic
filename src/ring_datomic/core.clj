(ns ring-datomic.core
  (:refer-clojure :exclude [replace])
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [datomic.client.api :as d]
            [clojure.string :refer [replace]])
  (:import [java.util.concurrent Executors]
           [org.eclipse.jetty.util.thread QueuedThreadPool])
  (:gen-class))

(def config {:server-type :datomic-local
             :system "db"})

(def schema [{:db/ident :movie/id
              :db/valueType :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc "The ID of the movie"
              :db/unique :db.unique/identity}
             {:db/ident :movie/title
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc "The title of the movie"}
             {:db/ident :movie/genre
              :db/valueType :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc "The genre of the movie"}
             {:db/ident :movie/release-year
              :db/valueType :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc "The year the movie was released in theaters"}])

(def data [{:movie/id 1
            :movie/title "The Goonies"
            :movie/genre "action/adventure"
            :movie/release-year 1985}
           {:movie/id 2
            :movie/title "Commando"
            :movie/genre "thriller/action"
            :movie/release-year 1985}
           {:movie/id 3
            :movie/title "Repo Man"
            :movie/genre "punk dystopia"
            :movie/release-year 1984}])

(defn handler
  [request]
  (let [client (d/client config)
        _ (d/delete-database client {:db-name "movies"})
        _ (d/create-database client {:db-name "movies"})
        conn (d/connect client {:db-name "movies"})]
    (let [xact #(d/transact conn {:tx-data %})
          id (-> (replace (:uri request) #"/" "")
                 (Integer/parseInt))]
      (xact schema)
      (xact data)
      {:status 200
       :headers {"Content-Type" "text/html"}
       :body (str (ffirst (d/q '[:find ?title
                                 :in $ ?id
                                 :where [?e :movie/id ?id]
                                 [?e :movie/title ?title]]
                               (d/db conn)
                               id)))})))

(defn -main
  [& [port]]
  (let [port (or port (get (System/getenv) "PORT" 3000))
        port (cond-> port (string? port) Integer/parseInt)
        thread-pool (QueuedThreadPool.)
        _ (.setVirtualThreadsExecutor thread-pool (Executors/newVirtualThreadPerTaskExecutor))]
    (run-jetty handler {:port port
                        :join? false
                        :thread-pool thread-pool})))
