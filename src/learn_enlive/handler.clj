(ns learn-enlive.handler
  (:require [compojure.core :refer [defroutes GET routes]]
            ;[compojure.route :as route]
            ;[ring.util.response :as response]
            ;[net.cgrand.enlive-html :as html]
            [learn-enlive.routes.login :refer [login-page]]
            [learn-enlive.routes.status :refer [status]]
            [learn-enlive.routes.extract :refer [extract]]
            [compojure.handler :as handler]
            )
  (:use [ring.middleware file file-info params session]))

(defn init []
  (println "guestbook is starting"))

(defn destroy []
  (println "guestbook is shutting down"))

(defroutes my-app
  (-> (routes login-page status extract)
      (wrap-file "resources/public")
      ;(wrap-file-info)
      wrap-params
      wrap-session))

