/**
 * Created by lkokhreidze on 3/24/16.
 */

app.controller('TreeController', TreeController);

function TreeController($resource) {
    var vm = this;
    vm.id = '';
    vm.loaded = false;
    vm.requestSent = false;

    vm.stats = {
        reducePercentage: '',
        originalServices: 0,
        reducedServices: 0
    };

    vm.getTree = function () {
        vm.requestSent = true;
        get(vm.id).$promise.then(function (data) {
            vm.loaded = true;
            vm.stats.reducePercentage = data[0].reducePercentage;
            vm.stats.originalServices = data[0].originalServices;
            vm.stats.reducedServices = data[0].reducedServices;
            var res = data[0].services;
            console.log(res);
            var chart_config = {
                chart: {
                    container: "#url-tree",
                    levelSeparation: 70,
                    siblingSeparation: 60,
                    nodeAlign: "BOTTOM",
                    connectors: {
                        type: "curve",
                        style: {
                            "stroke-width": 2,
                            "stroke": "#ccc",
                            "stroke-dasharray": "--",
                            "arrow-end": "classic-wide-long"
                        }
                    },
                    node: {
                        collapsable: true
                    }
                },
                nodeStructure: res
            };
            new Treant(chart_config);
        })
    };

    function get(id) {
        return $resource('/api/discovery/services/tree', {id: id}).query()
    }
}