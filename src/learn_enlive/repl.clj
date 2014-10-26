(ns learn-enlive.repl
  (:use learn-enlive.handler
        ring.server.standalone
        [ring.middleware file file-info params session]))

(defonce server (atom nil))

(defn get-handler []
  (-> #'my-app
      (wrap-file "resources/public")
      ;(wrap-file-info)
      wrap-params
      wrap-session
      ))

(defn start-server
  [& [port]]
  (let [port (if port
               (Integer/parseInt port)
               8080)]
    (reset!
      server
      (serve (get-handler)
             {:port port
              :init init
              :auto-reload? true
              :destroy destroy
              :join true}))
    (println (str "you can see site at http://localhost:" port))))



(defn stop-server []
  (.stop @server)
  (reset! server nil))
