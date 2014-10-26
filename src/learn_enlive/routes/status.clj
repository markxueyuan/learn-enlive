(ns learn-enlive.routes.status
  (:require [learn-enlive.models.selenium :refer :all]
            [compojure.core :refer :all]
            [ring.util.response :as response]
            [net.cgrand.enlive-html :as html]))


(html/deftemplate success "public/status.html"
                  [status]
                  [:#alert-failure] nil
                  [:#button-failure] nil
                  [:#username] (html/content (.getText status)))

(html/deftemplate failure "public/status.html"
                  []
                  [:#alert-success] nil
                  [:#button-success] nil)



(defn handler
  [request]
  (binding [my-driver (:session request)]
    (Thread/sleep 5000)
    (if-let [status (try (find-element :class-name "username")
                  (catch Throwable e nil))]
      (success status)
      (failure)
      )))


(defroutes status
  (GET "/status" request (do (println (:session request))
                             (handler request))))