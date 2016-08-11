'use strict';

angular.module("kataApp").factory("UserManagementService", UserManagementService);
UserManagementService.$inject = ['$q', '$http'];
  
function UserManagementService ($q, $http) {
    var baseUrl = 'user/';
    var services = {
        addUser : addUser,
        getUser : getUser
    };
    return services;
    
    function addUser(user) {
            var deferred = $q.defer();
            $http({
                    method : 'POST',
                    url :  baseUrl,
                    data : user
            }).success(function(data) {
                    deferred.resolve(data);
            }).error(function(data, status) {
                    var reponse = {}; 
                    reponse.data = data; 
                    reponse.status = status; 
                    deferred.reject(reponse);
            });
            return deferred.promise;
    }
    
    function getUser(login) {
            var deferred = $q.defer();
            $http.get(baseUrl + login)
            .success(function(data) {
                    deferred.resolve(data);
            }).error(function(msg) {
                    deferred.reject(msg);
            });
            return deferred.promise;
    }
  };
