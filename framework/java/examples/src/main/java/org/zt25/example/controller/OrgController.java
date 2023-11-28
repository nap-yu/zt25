package org.zt25.example.controller;

import lombok.RequiredArgsConstructor;
import org.zt25.web.ZController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/org")
@RequiredArgsConstructor
public class OrgController extends ZController {

    //private final OrgRepository repository;
    //
    //
    //@PostMapping(path="/add")
    //public OrgDto add(@Validated @RequestBody OrgDto dto){
    //    OrgEntity org = repository.add((OrgEntity)SupportBeanConverter.BEAN_TO_BEAN.convert(dto, OrgEntity.class));
    //    return (OrgDto)SupportBeanConverter.BEAN_TO_BEAN_HANDLER.convert(org, OrgDto.class);
    //}
    //
    //@GetMapping (path="/getCount/{code}")
    //public Long getCount(@PathVariable("code")  String code){
    //    Long count = repository.getBaseMapper().selectCount(code);
    //    return count;
    //}
    //
    //@GetMapping (path="/getByIdForWrapper/{code}")
    //public OrgDto getByIdForWrapper(@PathVariable("code")  String code){
    //    //Wrapper<OrgEntity> wrapper = repository.conditionToWrapper(new Condition());
    //
    //    QueryWrapper<OrgEntity> wrapper1 = new QueryWrapper<>();
    //    //wrapper1.eq("code",code);
    //    //QueryWrapper<OrgEntity> sub = new QueryWrapper<>();
    //    //sub.eq("code",code);
    //    //sub.like("code",code);
    //
    //    //wrapper1.nested(qw -> qw.eq("code", code).or().eq("code", code))
    //    //        .and(qw -> qw.nested(qw1 -> qw1.eq("name", "John").or().eq("name", "Jane"))
    //    //                .or().nested(qw2 -> qw2.eq("name", "Bob").or().eq("name", "Alice")));
    //
    //    MPJQueryWrapper<OrgEntity> wrapper2 = new MPJQueryWrapper<>();
    //
    //    MPJQueryWrapper<OrgEntity> wrapper = new MPJQueryWrapper<>();
    //    wrapper.selectAll(OrgEntity.class);
    //    //wrapper.leftJoin()
    //    wrapper.eq(wrapper.getAlias()+"."+"code",code);
    //
    //    System.out.println();
    //
    //    //wrapper.leftJoin();
    //
    //
    //
    //
    //
    //    String tableName = SqlHelper.table(OrgEntity.class).getTableName();
    //    System.out.println(tableName);
    //
    //
    //    OrgEntity org = repository.getBaseMapper().selectOne(wrapper);
    //    return (OrgDto)SupportBeanConverter.BEAN_TO_BEAN_HANDLER.convert(org, OrgDto.class);
    //}
    //
    ////@PostMapping (path="/createCondition")
    ////public Condition createCondition(@RequestBody Condition cond){
    ////    List<CondItem> items = new ArrayList<>();
    ////    CondItem item = new CondItem();
    ////
    ////    List<Field> fields = new ArrayList<>();
    ////    Field f = new Field();
    ////    f.setOperator(Operator.EQ);
    ////    f.setProperty("name");
    ////
    ////    fields.add(f);
    ////
    ////    item.setFields(fields);
    ////    items.add(item);
    ////    cond.setItems(items);
    ////    return cond;
    ////}


}
