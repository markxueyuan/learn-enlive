(defproject learn-enlive "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [ring-server "0.3.1"]
                 [enlive "1.1.5"]
                 [clj-webdriver "0.6.0"]
                 [org.seleniumhq.selenium/selenium-java "2.42.2"]
                 [org.seleniumhq.selenium/selenium-firefox-driver "2.42.2"]
                 [org.seleniumhq.selenium/selenium-server "2.42.2"]
                 [com.opera/operadriver "1.5"]
                 [com.github.detro.ghostdriver/phantomjsdriver "1.0.4"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler learn-enlive.handler/my-app})
