(ns bb.pages
  (:require
   [clojure.string :as str]
   [babashka.fs :as fs]
   [babashka.process :refer [shell]])
  (:import java.time.format.DateTimeFormatter
           java.time.Instant))

(defn timestamp-iso8601 []
  (let [fmt (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ss'Z'")]
    (.format fmt (.atZone (Instant/now) (java.time.ZoneId/of "Z")))))

(defn git-revision []
  (-> (shell {:out :string} "git rev-parse HEAD")
      :out str/trim))

(defn revision-span [timestamp sha]
  (str "<span id=\"revision\" title=\""
       timestamp
       "\"><code>rev:"
       (subs sha 0 8)
       "</code></span>"))

(defn build []
  (let [revision (git-revision)
        timestamp (timestamp-iso8601)
        out-dir (fs/file "static")]
    (fs/delete-tree out-dir)
    (fs/create-dir out-dir)
    (println "Building" (subs revision 0 8) "@" timestamp)
    (spit (fs/file out-dir "index.html")
          (-> (slurp "index.html")
              (str/replace-first "<span id=\"revision\"><code>rev:abcdef12</code></span>"
                                 (revision-span timestamp revision))))
    (spit (fs/file out-dir "style.css") (slurp "style.css"))))
