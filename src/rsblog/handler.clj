(ns rsblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [rsblog.db :as db]
            [rsblog.pages :as p]))

(defroutes app-routes
  (GET "/" []  (p/index (db/list-articles)))
  (GET "/articles/:art-id" [art-id] (p/article (db/get-article-by-id art-id)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
