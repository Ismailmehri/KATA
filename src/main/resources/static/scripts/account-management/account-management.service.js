'use strict';

angular.module("kataApp").factory("AccountManagementService",
    AccountManagementService);
AccountManagementService.$inject = ['$q', '$http'];
  
function AccountManagementService ($q, $http) {
    var baseUrl = 'account/',
        historyBaseUrl = 'history/';
    var services = {
        deposit : deposit,
        withdraw : withdraw,
        getUserAccount : getUserAccount,
        getUserHistory : getUserHistory
    };
    return services;
    
    function getUserHistory(id) 
    {
            var deferred = $q.defer();
            $http.get(historyBaseUrl + id)
            .success(function(data) {
                    deferred.resolve(data);
            }).error(function(msg) {
                    deferred.reject(msg);
            });
            return deferred.promise;
    }

    function deposit(id, amount) {
            var deferred = $q.defer();
            $http({
                    method : 'PUT',
                    url :  baseUrl + 'deposit/' + id,
                    data : amount
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
   
    function withdraw(id, amount) {
        var deferred = $q.defer();
        $http({
                method : 'PUT',
                url :  baseUrl + 'withdraw/' + id,
                data : amount
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
    
    function getUserAccount(userId) {
            var deferred = $q.defer();
            $http.get(baseUrl + 'byUserId/' + userId)
            .success(function(data) {
                    deferred.resolve(data);
            }).error(function(msg) {
                    deferred.reject(msg);
            });
            return deferred.promise;
    }
  };
