(ns learn-enlive.routes.extract
  (:import (java.io StringReader))
  (:require [learn-enlive.models.selenium :refer :all]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [net.cgrand.enlive-html :as html]
            [clojure.string :as s]))

(defn to-transaction-list
  []
  (click (find-element :link-text "浏览交易")))

(defn extract-entry
  [entry]
  (->> (html/select entry [:li])
       (map html/text)
       (map s/trim)
       (map #(str "<td>" % "</td>"))))

(defn scratch-transaction-list
  []
  (-> (execute-script "return document.documentElement.outerHTML;")
      StringReader.
      html/html-resource
      (html/select [:#sheetListInitBox :.list-item])
      (#(map extract-entry %))
      (#(map (partial apply str) %))
      (#(map (fn [row] (str "<tr>" row "</tr>")) %))
      ((partial apply str))
      (#(str
            "<caption>交易表</caption>"
            "<tbody>"
            %
            "</tbody>"))))

(html/deftemplate table "public/list.html"
                  [t]
                  [:#my-table]
                  (html/html-content t))




(defn handler
  [request]
  (binding [my-driver (:session request)]
    (Thread/sleep 5000)
    (to-transaction-list)
    (Thread/sleep 5000)
    (table (scratch-transaction-list))))

(defroutes extract
  (GET "/extract" request (do (println (:session request))
                             (handler request)
                             )))


