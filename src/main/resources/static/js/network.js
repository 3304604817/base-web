/**
 * 网络请求
 */
function AjaxGet(url) {
    let result = null;
    $.ajax({
        type: 'get',
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        cache: false,
        async: false,
        success: function (data) {
            result = data;
        },
        error: function (errorData) {
            const { errorMsg = null } = errorData.responseJSON;
            if(null != errorMsg){
                layer.msg(errorMsg);
            }
        }
    });
    return result;
}

function AjaxPost(url, body) {
    let result = null;
    $.ajax({
        type: 'post',
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(body),
        cache: false,
        async: false,
        success: function (data) {
            result = data;
        },
        error: function (errorData) {
            const { errorMsg = null } = errorData.responseJSON;
            if(null != errorMsg){
                layer.msg(errorMsg);
            }
        }
    });
    return result;
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
        },
        error: function (errorData) {
            const { errorMsg = null } = errorData.responseJSON;
            if(null != errorMsg){
                layer.msg(errorMsg);
            }
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
        },
        error: function (errorData) {
            const { errorMsg = null } = errorData.responseJSON;
            if(null != errorMsg){
                layer.msg(errorMsg);
            }
        }
    });
}