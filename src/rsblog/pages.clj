(ns rsblog.pages
  (:require [hiccup.page :refer [html5]]))

(defn base-page [& body]
  (html5
    [:head [:title "Salama Blog of Things"]]
    [:body
     [:a {:href "/"} [:h1 "Salama Blog of Things"]]
     body]))

(defn index [articles]
  (base-page
    (for [a articles]
      [:h2 [:a {:href (str "/articles/" (:_id a))} (:title a)]])))

(defn article [a]
  (base-page
    [:small (:created a)]
    [:h1 (:title a)]
    [:p (:body a)]))