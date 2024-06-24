(ns bb.static
  (:require
   [babashka.fs :as fs]
   [bb.pages.exhibitions :as exhibitions]
   [bb.pages.layout :as layout]
   [bb.pages.portfolio :as portfolio]
   [bb.pages.publications :as publications]
   [bb.revision :as rev]))

(defn page [{:keys [file title body]}]
  (println "Generating" (str file) "from"
           (subs (rev/git-revision) 0 8)
           "at"
           (rev/timestamp-iso8601))
  (spit file
        (layout/layout {:title title}
                       (layout/main body))))

(defn build [dir]
  (let [out-dir (fs/file dir)
        photo-dir (str out-dir "/photos")]
    (fs/delete-tree out-dir)
    (fs/create-dir out-dir)
    (fs/create-dir photo-dir)
    (page {:file (fs/file out-dir "index.html")
           :title "dgtized"
           :body [:article
                  (portfolio/section)
                  (exhibitions/section)
                  (publications/section)]})
    (fs/copy (fs/file "resources/public/style.css")
             (fs/file out-dir "style.css")
             {:replace-existing true})

    (doseq [f ["recursive-reveries1-600x900.jpeg"
               "strange-loop-sculpture.png"]]
      (fs/copy (fs/file "resources/public/photos" f)
               (fs/file photo-dir f)
               {:replace-existing true}))))

(comment (build "static"))
