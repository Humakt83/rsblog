(ns rsblog.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import
    [org.bson.types ObjectId]))

(def db-connection-uri (or (System/getenv "RSBLOG_MONGO_URI")
                           "mongodb://127.0.0.1/rsblog-test"))

(def db (-> db-connection-uri
            mg/connect-via-uri
            :db))

(def articles-coll "articles")

(defn create-article [title body]
  (mc/insert db articles-coll
            {:title title
             :body body
             :created (new java.util.Date)}))

(defn list-articles []
  (mc/find-maps db articles-coll))

(defn get-article-by-id [art-id]
  (mc/find-map-by-id db articles-coll (ObjectId. art-id)))