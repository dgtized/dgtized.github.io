(ns bb.pages.publications)

(def publications
  [{:authors "G. Adam Covington, Charles L.G. Comstock, et al."
    :title "High Speed Document Clustering in Reconfigurable Hardware"
    :place "Conference on Field Programmable Logic and Applications; August 2006"}
   {:authors "Adam Siepel, et al."
    :title "Targeted discovery of novel human exons by comparative genomics"
    :place "Genome Research 2007"}
   {:authors "The MGC Project Team, et al."
    :title "The completion of the Mammalian Gene Collection (MGC)"
    :place "Genome Research 2009"}
   {:authors "The modEncode Consortium, et al. "
    :title "Identification of Functional Elements and Regulatory Circuits by Drosophila modENCODE"
    :place "Science 2010"}])

(defn section []
  [:section
   [:h2 "Publications"]
   [:ul
    (for [{:keys [authors title place]} publications]
      [:li authors " " [:em title] "; " place])]])
