(ns learn-enlive.routes.login
  (:require [learn-enlive.models.selenium :refer :all]
            [compojure.core :refer :all]
            [ring.util.response :as response]))


(defn login-for-51
  [user pwd]
  (binding [my-driver (chrome)]
    (to "https://www.u51.com/money/")
    (Thread/sleep 2000)
    (-> (find-element :name "user_name_input")
        (input user))
    (-> (find-element :name "user_pwd_input")
        (input pwd))
    (Thread/sleep 1000)
    (click (find-element :class-name "login-btn"))
    my-driver))

(defn redirect
  [user pwd]
  (-> (response/redirect "/status")
      (assoc :session (login-for-51 user pwd))))



#_(defn login-for-douban
  [user pwd]
  (binding [my-driver (chrome)]
    (to "https://www.douban.com/accounts/login")
    (Thread/sleep 2000)
    (-> (find-element :name "form_email")
        (input user))
    (-> (find-element :name "form_password")
        (input pwd))
    (Thread/sleep 1000)
    (click (find-element :class-name "btn-submit"))))

(defn response
  []
  (response/resource-response "login.html" {:root "public"}))

(defroutes login-page
  (GET "/" [] (response))
  (POST "/" [myname mypwd] (redirect myname mypwd))
  ;(POST "/" request (println request))
  )


