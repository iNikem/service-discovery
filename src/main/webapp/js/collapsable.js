// var chart_config = {
//
//     chart: {
//         // animateOnInit: false,
//         // container: "#url-tree",
//         // levelSeparation: 25,
//         // siblingSeparation: 70,
//         // subTeeSeparation: 70,
//         // nodeAlign: "BOTTOM",
//         // // scrollbar: "fancy",
//         // padding: 35,
//         container: "#url-tree",
//         levelSeparation: 70,
//         siblingSeparation: 60,
//         nodeAlign: "BOTTOM",
//         connectors: {
//             type: "curve",
//             style: {
//                 "stroke-width": 2,
//                 "stroke": "#ccc",
//                 "stroke-dasharray": "--",
//                 "arrow-end": "classic-wide-long"
//             }
//         },
//         node: {
//             collapsable: true
//         }
//     },
//     // chart: {
//     //     animateOnInit: false,
//     //     container: "#url-tree",
//     //     levelSeparation: 25,
//     //     siblingSeparation: 70,
//     //     subTeeSeparation: 70,
//     //     nodeAlign: "BOTTOM",
//     //     padding: 35,
//     //     node: {
//     //         HTMLclass: "evolution-tree",
//     //         collapsable: false
//     //     },
//     //     connectors: {
//     //         type: "curve",
//     //         style: {
//     //             "stroke-width": 5,
//     //             "stroke-linecap": "round",
//     //             "stroke": "#ccc"
//     //         }
//     //     },
//     //     animation: {
//     //         nodeAnimation: "easeOutBounce",
//     //         nodeSpeed: 700,
//     //         connectorsAnimation: "bounce",
//     //         connectorsSpeed: 700
//     //     }
//     // },
//
//
//     nodeStructure: {
//         "text": {
//             "name": "/"
//         },
//         "HTMLclass": "the-parent",
//         "children": [
//             {
//                 "text": {
//                     "name": "connect"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_1"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "websocket"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "xhr_streaming"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "xhr"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "param_1"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "websocket"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "xhr_streaming"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "xhr"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "{1}"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "websocket"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "153"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "websocket"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "view"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "branch"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "job"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "branchsmoketest"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "param_0"
//                                                 },
//                                                 "HTMLclass": "the-parent",
//                                                 "children": [
//                                                     {
//                                                         "text": {
//                                                             "name": "testreport"
//                                                         }
//                                                     },
//                                                     {
//                                                         "text": {
//                                                             "name": "featurebranchbuild"
//                                                         }
//                                                     },
//                                                     {
//                                                         "text": {
//                                                             "name": "logtext"
//                                                         },
//                                                         "HTMLclass": "the-parent",
//                                                         "children": [
//                                                             {
//                                                                 "text": {
//                                                                     "name": "progressivehtml"
//                                                                 }
//                                                             }
//                                                         ],
//                                                         "collapsed": true
//                                                     }
//                                                 ],
//                                                 "collapsed": true
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "acceptance"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "job"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_2"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "descriptorbyname"
//                                                 },
//                                                 "HTMLclass": "the-parent",
//                                                 "children": [
//                                                     {
//                                                         "text": {
//                                                             "name": "param_0"
//                                                         },
//                                                         "HTMLclass": "the-parent",
//                                                         "children": [
//                                                             {
//                                                                 "text": {
//                                                                     "name": "param_1"
//                                                                 }
//                                                             }
//                                                         ],
//                                                         "collapsed": true
//                                                     }
//                                                 ],
//                                                 "collapsed": true
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "param_2"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "job"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_0"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "descriptorbyname"
//                                                 },
//                                                 "HTMLclass": "the-parent",
//                                                 "children": [
//                                                     {
//                                                         "text": {
//                                                             "name": "param_3"
//                                                         },
//                                                         "HTMLclass": "the-parent",
//                                                         "children": [
//                                                             {
//                                                                 "text": {
//                                                                     "name": "param_1"
//                                                                 }
//                                                             }
//                                                         ],
//                                                         "collapsed": true
//                                                     }
//                                                 ],
//                                                 "collapsed": true
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "sessions"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         }
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "job"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "branchsmoketest"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "logtext"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "progressivehtml"
//                                                 }
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "featurebranchbuild"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "artifact"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "testreport"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "rebuild"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             },
//                             {
//                                 "text": {
//                                     "name": "param_2"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "artifact"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "param_1"
//                                                 },
//                                                 "HTMLclass": "the-parent",
//                                                 "children": [
//                                                     {
//                                                         "text": {
//                                                             "name": "param_0"
//                                                         }
//                                                     }
//                                                 ],
//                                                 "collapsed": true
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             },
//                             {
//                                 "text": {
//                                     "name": "param_1"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_0"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "testreport"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "junit"
//                                                 },
//                                                 "HTMLclass": "the-parent",
//                                                 "children": [
//                                                     {
//                                                         "text": {
//                                                             "name": "param_3"
//                                                         },
//                                                         "HTMLclass": "the-parent",
//                                                         "children": [
//                                                             {
//                                                                 "text": {
//                                                                     "name": "param_2"
//                                                                 },
//                                                                 "HTMLclass": "the-parent",
//                                                                 "children": [
//                                                                     {
//                                                                         "text": {
//                                                                             "name": "param_0"
//                                                                         }
//                                                                     }
//                                                                 ],
//                                                                 "collapsed": true
//                                                             }
//                                                         ],
//                                                         "collapsed": true
//                                                     }
//                                                 ],
//                                                 "collapsed": true
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "portalaccept"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "console"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "rebuild"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "param_1"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "console"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             },
//                             {
//                                 "text": {
//                                     "name": "descriptorbyname"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_0"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "param_2"
//                                                 }
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "acceptancetestapplication"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "descriptorbyname"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_1"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "param_0"
//                                                 }
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "agent"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_1"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "epicdeploy"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "ws"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_1"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "param_0"
//                                                 }
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             },
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "logtext"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "progressivehtml"
//                                                 }
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "dashboard"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 }
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "param_0"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_1"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "console"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             },
//                             {
//                                 "text": {
//                                     "name": "descriptorbyname"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "param_2"
//                                         },
//                                         "HTMLclass": "the-parent",
//                                         "children": [
//                                             {
//                                                 "text": {
//                                                     "name": "param_1"
//                                                 }
//                                             }
//                                         ],
//                                         "collapsed": true
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "feedback"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         }
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "param_0"
//                 }
//             },
//             {
//                 "text": {
//                     "name": "s"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "probedump"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "user_transaction"
//                                         }
//                                     },
//                                     {
//                                         "text": {
//                                             "name": "used_memory_v3"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "incidents"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "affected-transactions"
//                                 }
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "info"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "header"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_0"
//                                 }
//                             }
//                         ],
//                         "collapsed": true
//                     },
//                     {
//                         "text": {
//                             "name": "param_0"
//                         }
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "communication"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "param_1"
//                                 }
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "usage"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "param_0"
//                         }
//                     }
//                 ],
//                 "collapsed": true
//             },
//             {
//                 "text": {
//                     "name": "epicbuild"
//                 },
//                 "HTMLclass": "the-parent",
//                 "children": [
//                     {
//                         "text": {
//                             "name": "job"
//                         },
//                         "HTMLclass": "the-parent",
//                         "children": [
//                             {
//                                 "text": {
//                                     "name": "{1}"
//                                 },
//                                 "HTMLclass": "the-parent",
//                                 "children": [
//                                     {
//                                         "text": {
//                                             "name": "rebuild"
//                                         }
//                                     }
//                                 ],
//                                 "collapsed": true
//                             }
//                         ],
//                         "collapsed": true
//                     }
//                 ],
//                 "collapsed": true
//             }
//         ],
//         "collapsed": false
//     }
//
// };
