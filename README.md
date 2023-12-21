### LOCAL STORAGE
```bash
$ more ~/.datomic/local.edn 
{:storage-dir "/home/rasyid/Sources/clojure/ring/ring-datomic"}
```
### RUN
```bash
$ clojure -M:run
[main] INFO org.eclipse.jetty.server.Server - jetty-11.0.18; built: 2023-10-27T02:14:36.036Z; git: 5a9a771a9fbcb9d36993630850f612581b78c13f; jvm 21+35-LTS
[main] INFO org.eclipse.jetty.server.handler.ContextHandler - Started o.e.j.s.ServletContextHandler@57421698{/,null,AVAILABLE}
[main] INFO org.eclipse.jetty.server.AbstractConnector - Started ServerConnector@1dc37d4f{HTTP/1.1, (http/1.1)}{0.0.0.0:3000}
[main] INFO org.eclipse.jetty.server.Server - Started Server@34dc40d5{STARTING}[11.0.18,sto=0] @11681ms
```
### CHECK
```
http://localhost:3000/1 # The Goonies
http://localhost:3000/2 # Commando
http://localhost:3000/3 # Repo Man
```