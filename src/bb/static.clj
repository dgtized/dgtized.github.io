(ns bb.static
  (:require
   [clojure.string :as str]
   [babashka.process :refer [shell]]
   [hiccup.element :as he]
   [hiccup.page :as hp])
  (:import java.time.format.DateTimeFormatter
           java.time.Instant))

(defn timestamp-iso8601 []
  (let [fmt (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ss'Z'")]
    (.format fmt (.atZone (Instant/now) (java.time.ZoneId/of "Z")))))

(defn git-revision []
  (-> (shell {:out :string} "git rev-parse HEAD")
      :out str/trim))

(defn revision [timestamp sha]
  [:span {:id "reversion" :title timestamp}
   [:code (str "rev:" (subs sha 0 8))]])

(defn footer []
  [:footer
   [:span "© 2022-2024 Charles L.G. Comstock"]
   [:span
    (for [[link href] [["email" "mailto:dgtized@gmail.com"]
                       ["github" "https://github.com/dgtized"]
                       ["twitter" "https://twitter.com/dgtized"]
                       ["instagram" "https://instagram.com/dgtized"]
                       ["flickr" "https://flickr.com/dgtized"]]]
      (he/link-to href (str "(" link ")")))]
   (revision (timestamp-iso8601) (git-revision))])

(defn layout [{:keys [title]} body]
  (hp/html5
   [:head
    [:title title]
    (hp/include-css "style.css")]
   [:body
    body
    (footer)]))

(comment (layout {:title "test"} "body"))

