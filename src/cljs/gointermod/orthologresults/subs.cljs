(ns gointermod.orthologresults.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
  :search-results
  (fn [db]
    (reaction (:search-results @db))))

(re-frame/register-sub
  :aggregate-results
  (fn [db]
    (reaction (:aggregate-results @db))))
