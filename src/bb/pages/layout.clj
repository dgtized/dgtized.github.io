(ns bb.pages.layout
  (:require
   [bb.revision :as rev]
   [hiccup.element :as he]
   [hiccup.page :as hp]))

(defn revision [timestamp sha]
  [:span {:id "reversion" :title timestamp}
   [:code (str "rev:" (subs sha 0 8))]])

(defn footer []
  [:footer
   [:span "© 2022-2024 Charles L.G. Comstock "]
   [:span
    (for [[link href] [["email" "mailto:dgtized@gmail.com"]
                       ["github" "https://github.com/dgtized"]
                       ["twitter" "https://twitter.com/dgtized"]
                       ["instagram" "https://instagram.com/dgtized"]
                       ["flickr" "https://flickr.com/dgtized"]]]
      [:span (he/link-to href (str "(" link ")")) " "])]
   (revision (rev/timestamp-iso8601) (rev/git-revision))])

(defn layout [{:keys [title]} body]
  (hp/html5
   {:xml? true}
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "ie=edge"}]
    [:title title]
    (hp/include-css "style.css")]
   [:body body]))

(defn header []
  [:header
   [:h1 {:class "center signature"} "Charles Comstock"]
   [:p {:class "center tagline"} "Software Developer & Maker"]])

(defn main [body]
  [:main
   (header)
   body
   (footer)])

(comment (layout {:title "test"}
                 (main "body")))
