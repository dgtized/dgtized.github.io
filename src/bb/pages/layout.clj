(ns bb.pages.layout
  (:require
   [bb.revision :as rev]
   [hiccup.element :as he]
   [hiccup.page :as hp]))

(defn revision [timestamp sha]
  [:span {:id "reversion" :title timestamp}
   [:code (str "rev:" (subs sha 0 8))]])

(def footer-links
  [{:link "email" :href "mailto:dgtized@gmail.com"}
   {:link "github" :href "https://github.com/dgtized"}
   {:link "twitter" :href "https://twitter.com/dgtized"}
   {:link "instagram" :href "https://instagram.com/dgtized"}
   {:link "flickr" :href "https://flickr.com/dgtized"}])

(defn footer []
  [:footer
   [:span "© 2022-2025 Charles L.G. Comstock "]
   [:span
    (for [{:keys [link href]} footer-links]
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
