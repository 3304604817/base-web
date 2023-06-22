/**
 * 网络请求
 */
function AjaxGet(url) {
    $.ajax({
        type: 'get',
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        async: false,
        success: function (data) {
            return data;
        }
    });
}

function AjaxPost(url, body) {
    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(body),
        cache: false,
        async: false,
        success: function (data) {
            return data;
        }
    });
}

function AjaxPut(url, body) {
    $.ajax({
        type: 'put',
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(body),
        cache: false,
        async: false,
        success: function (data) {
            return data;
        }
    });
}

function AjaxDelete(url, body) {
    $.ajax({
        type: 'delete',
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(body),
        cache: false,
        async: false,
        success: function (data) {
            return data;
        }
    });
}