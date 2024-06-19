(ns bb.static
  (:require [hiccup.page :as hp]))

(defn layout [{:keys [title]} body]
  (hp/html5
   [:head
    [:title title]
    (hp/include-css "style.css")]
   [:body
    body]))

(comment (layout {:title "test"} "body"))

