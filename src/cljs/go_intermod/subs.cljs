(ns go-intermod.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))


(re-frame/register-sub
 :organisms
 (fn [db]
  (reaction (:organisms @db))))

(re-frame/register-sub
 :evidence-codes
 (fn [db]
   (reaction (:evidence-codes @db))))
