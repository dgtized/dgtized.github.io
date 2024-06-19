(ns bb.static
  (:require
   [clojure.string :as str]
   [babashka.fs :as fs]
   [babashka.process :refer [shell]]
   [bb.pages.exhibitions :as exhibitions]
   [bb.pages.portfolio :as portfolio]
   [bb.pages.publications :as publications]
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
   [:span "© 2022-2024 Charles L.G. Comstock "]
   [:span
    (for [[link href] [["email" "mailto:dgtized@gmail.com"]
                       ["github" "https://github.com/dgtized"]
                       ["twitter" "https://twitter.com/dgtized"]
                       ["instagram" "https://instagram.com/dgtized"]
                       ["flickr" "https://flickr.com/dgtized"]]]
      [:span (he/link-to href (str "(" link ")")) " "])]
   (revision (timestamp-iso8601) (git-revision))])

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

(defn build []
  (let [out-dir (fs/file "static")]
    (fs/delete-tree out-dir)
    (fs/create-dir out-dir)
    (println "Generating index.html from"
             (subs (git-revision) 0 8)
             "at"
             (timestamp-iso8601))
    (spit (fs/file out-dir "index.html")
          (layout {:title "dgtized"}
                  (main [:article
                         (portfolio/section)
                         (exhibitions/section)
                         (publications/section)])))
    (fs/copy (fs/file "resources/public/style.css") (fs/file out-dir "style.css")
             {:replace-existing true})))

(comment (build))
