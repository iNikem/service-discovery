/**
 * Created by lkokhreidze on 3/24/16.
 */

app.controller('TreeController', TreeController);

function TreeController($resource) {
    var vm = this;
    vm.id = '';
    vm.loaded = false;
    vm.requestSent = false;

    vm.graph = {};
    vm.stats = {
        reductionPercentage: '',
        reducedServicesSize: 0,
        urlServicesSize: 0,
        ignoredNoiseSize: 0,
        ignoredControllerServices: 0,
        total: 0
    };

    vm.getTree = function () {
        vm.requestSent = true;

        get(vm.id).$promise.then(function (data) {
            vm.loaded = true;
            vm.stats.reductionPercentage = data.reductionPercentage;
            vm.stats.reducedServicesSize = data.reducedServicesSize;
            vm.stats.urlServicesSize = data.urlServicesSize;
            vm.stats.ignoredNoiseSize = data.ignoredNoiseSize;
            vm.stats.ignoredControllerServices = data.ignoredControllerServices;
            vm.stats.total = data.total;
            vm.graph = data.graph;
            console.log(vm.stats);
            console.log(vm.graph);
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
                nodeStructure: vm.graph
            };
            try {
                new Treant(chart_config);
            } catch(e) {}
        })
    };

    function get(id) {
        return $resource('/api/discovery/services/tree', {id: id}).get()
    }
}