(ns gointermod.orthologresults.views
    (:require [re-frame.core :as re-frame]
      [gointermod.utils.utils :as utils]
))

(defn headers []
  [:thead [:tr
  [:th "Species"]
  [:th "Orthologs"]
  [:th "GO identifier thingy"]
  [:th "Term"]
  [:th "Branch"]
   ]])

 (defn result-row [[original-symbol original-secondary-id original-id _ _ _ homie-id homie-secondary-id homie-symbol homie-organism _ data-set  _ pub-id _ go-identifier ontology-term ontology-branch]]
   ^{:key (gensym)}
   [:tr
   [:td homie-organism]
   [:td homie-symbol]
   [:td go-identifier]
   [:td ontology-term]
   [:td ontology-branch]
    ])

(defn results []
  "output search results into table rows"
  (let [search-results (re-frame/subscribe [:search-results])]
  [:tbody (map result-row (:results @search-results))]))

(defn count-by-ontology-branch [branch]
  (let [search-results (:results @(re-frame/subscribe [:search-results]))]
  (count (filter
   (fn [result] (= (last result) branch)) search-results))
))

(defn aggregate-headers []
  [:thead [:tr
  [:th "Include"]
  [:th "Species"]
  [:th "Orthologs"]
  [:th.count "Biological Process"]
  [:th.count "Molecular Function"]
  [:th.count "Cellular Component"]
  ]])

(defn aggregate-results []
  "output aggregated search results into table rows"
  (let [results (re-frame/subscribe [:aggregate-results])]
    [:tbody
     (map (fn [[organism organism-details] organisms]
        (map (fn [[k v] organism-details]
           ^{:key (gensym)}
           [:tr
            [:td [:input {:type "checkbox"}]]
            [:td (clj->js organism)]
            [:td (clj->js k)]
              [:td (:biological_process v)]
              [:td (:molecular_function v)]
              [:td (:cellular_component v)]
            ]) organism-details)
        ) @results)]))


(defn orthologs []
  (fn []
     [:div.ortholog-results
      [:h2 "Orthologous Genes"]
      [:table.aggregate
        [aggregate-headers]
        [aggregate-results]]
      [:table
        [headers]
        [results]
        ]]))
