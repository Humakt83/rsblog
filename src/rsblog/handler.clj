(ns rsblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [rsblog.db :as db]
            [rsblog.pages :as p]))

(defroutes app-routes
  (GET "/" []  (p/index (db/list-articles)))
  (GET "/articles/new" [] (p/edit-article nil))
  (POST "/articles" [title body]
    (do (db/create-article title body)
        (resp/redirect "/")))
  (GET "/articles/:art-id" [art-id] (p/article (db/get-article-by-id art-id)))
  (GET "/articles/:art-id/edit" [art-id] (p/edit-article (db/get-article-by-id art-id)))
  (POST "/articles/:art-id" [art-id title body]
    (do (db/update-article art-id title body)
        (resp/redirect (str "/articles/" art-id))))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
