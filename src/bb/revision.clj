(ns bb.revision
  (:require
   [clojure.string :as str]
   [babashka.process :refer [shell]])
  (:import java.time.format.DateTimeFormatter
           java.time.Instant))


(defn timestamp-iso8601 []
  (let [fmt (DateTimeFormatter/ofPattern "yyyy-MM-dd'T'HH:mm:ss'Z'")]
    (.format fmt (.atZone (Instant/now) (java.time.ZoneId/of "Z")))))

(defn git-revision []
  (-> (shell {:out :string} "git rev-parse HEAD")
      :out str/trim))


