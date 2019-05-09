var map;
require(["dojo/parser", "dojo/on", "esri/map", "esri/geometry/Extent",
        "esri/SpatialReference", "esri/layers/ArcGISTiledMapServiceLayer", "esri/layers/ArcGISDynamicMapServiceLayer", "esri/geometry/Point",
        "esri/tasks/FindTask", "esri/tasks/FindParameters", "esri/InfoTemplate",
        "esri/symbols/SimpleMarkerSymbol", "esri/symbols/SimpleLineSymbol", "esri/symbols/SimpleFillSymbol", "esri/Color",
        "dijit/layout/BorderContainer", "dijit/layout/ContentPane", "dojo/domReady!"
    ],
    function (parser, on, Map, Extent,
        SpatialReference, ArcGISTiledMapServiceLayer, ArcGISDynamicMapServiceLayer, Point,
        FindTask, FindParameters, InfoTemplate,
        SimpleMarkerSymbol, SimpleLineSymbol, SimpleFillSymbol, Color) {
        parser.parse();

        //初始地图页面的位置参数及wkid    
        var startExtent = new Extent(113.34272817600004, 23.02640584100004, 113.42470842100003, 23.08441803500004, new SpatialReference({
            wkid: 4326
        }));
        map = new Map("mapDiv", {
            extent: startExtent
        });



        // 动态图
        var usaBase = new ArcGISDynamicMapServiceLayer("http://localhost:6080/arcgis/rest/services/homeMap/MapServer");
        // 设置要显示的图层
        usaBase.setVisibleLayers([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13]);
        // 设置图层透明度
        usaBase.setOpacity(1);
        map.addLayer(usaBase);

        // 实例化FindTask
        var findTask = new FindTask("http://localhost:6080/arcgis/rest/services/homeMap/MapServer");
        // FindTask的参数
        var findParams = new FindParameters();
        // 返回Geometry
        findParams.returnGeometry = true;
        // 查询的图层id
        findParams.layerIds = [0, 1];
        // 查询字段
        findParams.searchFields = ["name"];

        //new SimpleMarkerSymbol(style, size, outline, color)	通过参数创建一个点符号对象
        //new SimpleLineSymbol(线型,线颜色,线宽,线的透明度)
        var ptSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 20, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID, new Color([255, 255, 0]), 1), new Color([255, 0, 0, 1]));
        var lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASH, new Color([255, 0, 0]), 1);
        var polygonSymbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_NONE, new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT, new Color([255, 0, 0]), 2), new Color([255, 255, 0, 0.25]));

        on(document.getElementById("findBtn"), "click", function () {
            execute(document.getElementById('searchText').value);
        });

        // 根据输入的关键字进行findTask操作
        function execute(searchText) {
            findParams.searchText = searchText;
            findTask.execute(findParams, showResults);
        }

        // 显示findTask的结果
        function showResults(results) {
            // 清除上一次的高亮显示
            map.graphics.clear();

            var innerHtml = "";
            var symbol;
            for (var i = 0; i < results.length; i++) {
                var curFeature = results[i];
                var graphic = curFeature.feature;
                var infoTemplate = null;

                // 根据类型设置显示样式
                switch (graphic.geometry.type) {
                    case "point":
                        symbol = ptSymbol;
                        infoTemplate = new InfoTemplate("${name}", "${*}");
                        break;
                    case "polyline":
                        var symbol = lineSymbol;
                        break;
                    case "polygon":
                        var symbol = polygonSymbol;
                        break;
                }
                // 设置显示样式
                graphic.setSymbol(symbol);
                graphic.setInfoTemplate(infoTemplate);
                // 添加到graphics进行高亮显示
                map.graphics.add(graphic);

                if (curFeature.layerId === 0) {
                    innerHtml += "<style> a{color:rgb(63, 62, 62);font-size:16px;text-decoration:none;font-style:黑体;margin-left:7px;} a:hover{color:blue;}</style>"
                    +"<a href='javascript:positionFeature(" + graphic.attributes.OBJECTID + ")'>" + graphic.attributes.name + "</a><br>";
                } else if (curFeature.layerId === 1) {
                    innerHtml += "<style> a{color:rgb(63, 62, 62);font-size:16px;text-decoration:none;font-style:黑体;margin-left:7px;} a:hover{color:blue;}</style>" +
                        "<a href='javascript:positionFeature(" + graphic.attributes.OBJECTID + ")'>" + graphic.attributes.name + "</a><br>";
                } else {
                    innerHtml += "<style> a{color:rgb(63, 62, 62);font-size:16px;text-decoration:none;font-style:黑体;margin-left:7px;} a:hover{color:blue;}</style>" +
                        "<a href='javascript:positionFeature(" + graphic.attributes.OBJECTID + ")'>" + graphic.attributes.name + "</a><br>";
                }
            }

            document.getElementById("contentsContainer").innerHTML = innerHtml;
        }

        window.positionFeature = function (id) {
            var sGrapphic;
            //遍历地图的图形查找FID和点击行的FID相同的图形
            for (var i = 0; i < map.graphics.graphics.length; i++) {
                var cGrapphic = map.graphics.graphics[i];
                if ((cGrapphic.attributes) && cGrapphic.attributes.OBJECTID == id) {
                    sGrapphic = cGrapphic;
                    break;
                }
            }

            var sGeometry = sGrapphic.geometry;
            // 当点击的名称对应的图形为点类型时进行地图中心定位显示
            if (sGeometry.type == "point") {
                var cPoint = new Point();
                cPoint.x = sGeometry.x;
                cPoint.y = sGeometry.y;
                map.centerAt(cPoint);

                var p = map.toScreen(sGrapphic.geometry);
                var iw = map.infoWindow;
                iw.setTitle(sGrapphic.getTitle());
                // iw.setContent(sGrapphic.getContent());
                var content = "地址:" + sGrapphic.attributes.address + "<br>";
                content += "电话:" + sGrapphic.attributes.tel+ "<br>";
                content += "简介:" + sGrapphic.attributes.introduction + "<br>";
                content += "<a href=" + sGrapphic.attributes.url + ">" + "预约体验" + "</a>";
                content += "<a href=" + sGrapphic.attributes.url2 + ">" + "店铺下单" + "</a>";
                iw.setContent(content);
                iw.show(p, map.getInfoWindowAnchor(p));
            }
            //当点击的名称对应的图形为线或面类型时获取其范围进行放大显示
            else {
                var sExtent = sGeometry.getExtent();
                sExtent = sExtent.expand(2);
                map.setExtent(sExtent);
            }
        };
    });
/*
function positionFeature(id) {
    require(["esri/geometry/Point"], function (Point) {
        var sGrapphic;
        //遍历地图的图形查找FID和点击行的FID相同的图形
        for (var i = 0; i < map.graphics.graphics.length; i++) {
            var cGrapphic = map.graphics.graphics[i];
            if ((cGrapphic.attributes) && cGrapphic.attributes.FID == id) {
                sGrapphic = cGrapphic;
                break;
            }
        }

        var sGeometry = sGrapphic.geometry;
        // 当点击的名称对应的图形为点类型时进行地图中心定位显示
        if (sGeometry.type == "point") {
            var cPoint = new Point();
            cPoint.x = sGeometry.x;
            cPoint.y = sGeometry.y;
            map.centerAt(cPoint);

            var p = map.toScreen(sGrapphic.geometry);
            var iw = map.infoWindow;
            iw.setTitle(sGrapphic.getTitle());
            iw.setContent(sGrapphic.getContent());
            iw.show(p, map.getInfoWindowAnchor(p));
        }
            //当点击的名称对应的图形为线或面类型时获取其范围进行放大显示
        else {
            var sExtent = sGeometry.getExtent();
            sExtent = sExtent.expand(2);
            map.setExtent(sExtent);
        }
    });    
}*/
