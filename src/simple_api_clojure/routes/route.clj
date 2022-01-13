(ns simple-api-clojure.routes.route
    (:require [compojure.api.sweet :refer [POST]]
              [simple-api-clojure.handlers.handler :as api]
              [simple-api-clojure.schemas.user :refer [UserRequestSchema]]))


(def user-routes
    [(POST "/users" []
        :body-params [create-user-req UserRequestSchema]
        (-> (api/create-user-handler create-user-req)))])