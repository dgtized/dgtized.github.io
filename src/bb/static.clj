(ns bb.static
  (:require
   [babashka.fs :as fs]
   [bb.pages.exhibitions :as exhibitions]
   [bb.pages.layout :as layout]
   [bb.pages.portfolio :as portfolio]
   [bb.pages.publications :as publications]
   [bb.revision :as rev]))

(defn build []
  (let [out-dir (fs/file "static")]
    (fs/delete-tree out-dir)
    (fs/create-dir out-dir)
    (println "Generating index.html from"
             (subs (rev/git-revision) 0 8)
             "at"
             (rev/timestamp-iso8601))
    (spit (fs/file out-dir "index.html")
          (layout/layout {:title "dgtized"}
                         (layout/main [:article
                                       (portfolio/section)
                                       (exhibitions/section)
                                       (publications/section)])))
    (fs/copy (fs/file "resources/public/style.css") (fs/file out-dir "style.css")
             {:replace-existing true})))

(comment (build))
