(ns rsblog.web
  (:require
    [ring.adapter.jetty :as jetty]
    [compojure.handler :as ch]
    [rsblog.handler :as blog])
  (:gen-class))

(defn -main [& args]
  (let [port (Integer/valueOf (or (System/getenv "RSBLOG_PORT")
                 3000))]
    (jetty/run-jetty (ch/site #'blog/app)
                     {:port port
                      :join? false})))