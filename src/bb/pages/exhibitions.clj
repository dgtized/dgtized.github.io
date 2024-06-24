(ns bb.pages.exhibitions
  (:require [hiccup.element :as he]))

(def projects
  [{:project "\"Recursive Reveries\""
    :description "A tree of six recycled monitors displaying generative animations"
    :image "photos/recursive-reveries1-600x900.jpeg"
    :exhibitions
    [{:event (he/link-to "https://www.digitalephemera.art/" "Digital Ephemera")
      :place "St. Louis, MO"
      :date "March 29th, 2024"}]}
   {:project (he/link-to "https://dgtized.github.io/lemniscate"
                         "\"Lemniscate\"")
    :description "An interactive, programmable LED sculpture"
    :image "photos/strange-loop-sculpture.png"
    :exhibitions
    [{:event (he/link-to "https://www.thestrangeloop.com/"
                         "Strange Loop 2022")
      :date "September 23-24, 2022" :place "St. Louis, MO"}
     {:event (he/link-to "https://articastl.com" "Artica")
      :date "October 8-9, 2022" :place "St. Louis, MO"}
     {:event (he/link-to "https://www.neonmuseumstl.com/" "Neon Musuem")
      :date "October 2022 - August 2023" :place "St. Louis, MO"}
     {:event (he/link-to "https://www.thestrangeloop.com/"
                         "Strange Loop 2023")
      :date "September 21-22, 2023" :place "St. Louis, MO"}
     {:event (he/link-to "https://www.instagram.com/screwedarts/"
                         "Screwed Arts Collective")
      :date "December 9, 2023"
      :place "St. Louis, MO"}]}])

(defn section []
  [:section
   [:h2 "Exhibitions"]
   [:ul
    (for [{:keys [project description exhibitions image]} projects]
      [:li
       [:span project " " (he/link-to image "(photo)") " - " description]
       [:ul
        (for [{:keys [event place date]} exhibitions]
          [:li event " - " [:em place] " " date])]])]])
