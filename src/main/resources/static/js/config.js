/**
 * 路径 /basic 必需与gateway断言路径匹配规则一致（断言有"/"此处就需要有"/"），否则会出现路径自动拼多拼一个/basic异常
 * 单独跑微服务调试修改路径为 var basicPath = ''
 */
var basicPath = '';
// var basicPath = '/basic';
// var basePath = '/base';

/**
 * 正则校验
 */
var emailFormat = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
var phoneFormat = /^1\d{10}$/;