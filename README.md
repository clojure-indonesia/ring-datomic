### LOCAL STORAGE
```bash
$ more ~/.datomic/local.edn 
{:storage-dir "/home/rasyid/Sources/clojure/ring/ring-datomic"}
```
### RUN
```bash
$ clojure -M:run
[main] INFO org.eclipse.jetty.server.Server - jetty-11.0.18; built: 2023-10-27T02:14:36.036Z; git: 5a9a771a9fbcb9d36993630850f612581b78c13f; jvm 21+35-LTS
[main] INFO org.eclipse.jetty.server.handler.ContextHandler - Started o.e.j.s.ServletContextHandler@47b6f580{/,null,AVAILABLE}
[main] INFO org.eclipse.jetty.server.AbstractConnector - Started ServerConnector@4e739968{HTTP/1.1, (http/1.1)}{0.0.0.0:3000}
[main] INFO org.eclipse.jetty.server.Server - Started Server@4addfb95{STARTING}[11.0.18,sto=0] @12070ms
```
### CHECK
```
http://localhost:3000/Commando
```