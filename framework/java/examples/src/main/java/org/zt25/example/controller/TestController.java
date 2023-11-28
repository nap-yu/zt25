package org.zt25.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zt25.web.ZController;
import org.zt25.web.annotation.Decrypt;
import org.zt25.web.annotation.Encrypt;

@RestController
@RequestMapping(path="/test")
@RequiredArgsConstructor
public class TestController extends ZController {

    //private final DemoRepository repository;
    //
    //
    //
    ///**
    // * 获取请求ip
    // * @return {@link R}
    // */
    //@GetMapping(path="/getIp")
    //public R<String> getClientIp(){
    //    return this.success(this.getIp());
    //}
    //
    ///**
    // * 获取请求设备信息
    // * @return {@link R}
    // */
    //@GetMapping(path="/getDevice")
    //public R<DeviceInfo> getClientDevice(){
    //    return this.success(this.getDevice());
    //}
    //
    ///**
    // * 返回数据未字符串
    // * @return {@link R}
    // */
    //@PostMapping(path="/getString")
    //public String getString(@RequestBody String data){
    //    return data;
    //}
    //
    ///**
    // * 出入参加解密
    // * @return {@link R}
    // */
    //@PostMapping(path="/getEncrypt")
    //@Encrypt
    //public R<String> getEncrypt(@RequestBody @Decrypt String data){
    //    return this.success(data);
    //}
    //
    ///**
    // * 出入参加解密
    // * @return {@link R}
    // */
    @PostMapping(path="/getEncryptString")
    @Encrypt
    public String getEncryptString(@RequestBody @Decrypt String data){
        return "22222";
    }
    //
    ///**
    // * 测试异常返回
    // * @return {@link R}
    // */
    //@GetMapping(path="/testExceptionReturn")
    //public R<String> testExceptionReturn() throws Exception {
    //    //throw ExceptionFactory.instance(SystemException.class, SysError.SYSTEM_EXCEPTION,"这是一个手动抛出的异常");
    //    throw new Exception("这是一个手动抛出的异常");
    //}
    //
    ///**
    // * 添加数据
    // * @return {@link R}
    // */
    //@PostMapping(path="/saveDemo")
    //public R<Demo> saveDemo(@Validated @RequestBody Demo demo){
    //    this.repository.add(demo);
    //    return this.success(demo);
    //}
    //
    ///**
    // * 添加数据
    // * @return {@link R}
    // */
    //@PostMapping(path="/saveBatch")
    //public R<List<Demo>> saveBatch(@RequestBody List<Demo> demos){
    //    this.repository.addBatch(demos);
    //    return this.success(demos);
    //}
    //
    ///**
    // * 通过id删除
    // * @return {@link R}
    // */
    //@DeleteMapping(path="/delDemoById/{id}")
    //public R<Demo> delDemoById(@PathVariable String id){
    //    this.repository.remove(id);
    //    return this.success();
    //}
    //
    ///**
    // * 通过对象删除
    // * @return {@link R}
    // */
    //@DeleteMapping(path="/delDemoByEntity")
    //public R<Demo> delDemoByEntity(@RequestBody Demo demo){
    //    this.repository.remove(demo);
    //    return this.success();
    //}
    //
    ///**
    // * 通过id批量删除
    // * @return {@link R}
    // */
    ////@DeleteMapping(path="/delDemoByIds")
    ////public R<Demo> delDemoByIds(@RequestBody List<String> ids){
    ////    this.repository.remove(ids);
    ////    return this.success();
    ////}
    //
    ///**
    // * 通过对象批量删除
    // * @return {@link R}
    // */
    //@DeleteMapping(path="/delDemoByEntities")
    //public R<Demo> delDemoByEntities(@RequestBody List<Demo> entities){
    //    this.repository.remove(entities);
    //    return this.success();
    //}
    //
    //@PostMapping(path="/multipleObject")
    //public R<Demo> multipleObject(@MultipleObject Demo entitiy1,@MultipleObject Demo entitiy2){
    //    //this.repository.remove(entities);
    //    return this.success();
    //}

}
