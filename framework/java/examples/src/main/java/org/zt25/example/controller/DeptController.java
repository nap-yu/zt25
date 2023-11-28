package org.zt25.example.controller;

import lombok.RequiredArgsConstructor;
import org.zt25.example.entity.purview.DeptEntity;
import org.zt25.example.services.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/dept")
@RequiredArgsConstructor
public class DeptController {

    @Autowired
    private final DeptService service;

    //@PostMapping(path="/add")
    //public DeptDto add(@Validated @RequestBody DeptDto dto){
    //    return (DeptDto) SupportBeanConverter.BEAN_TO_BEAN.convert(service.add((DeptEntity)SupportBeanConverter.BEAN_TO_BEAN.convert(dto, DeptEntity.class)), DeptDto.class);
    //    //return null;
    //}

    //@PostMapping(path="/find")
    //public List<DeptEntity> find(@RequestBody DeptCo co){
    //
    //    return service.find(co);
    //
    //    //return null;
    //
    //    //MPJQueryWrapper<DeptEntity> wrapper = new MPJQueryWrapper<>(DeptEntity.class);
    //    //wrapper.selectAll(DeptEntity.class);
    //    //wrapper.leftJoin(SqlHelper.table(OrgEntity.class).getTableName()+" org on "+wrapper.getAlias()+".org_id = org.id");
    //    //wrapper.in(wrapper.getAlias()+'.'+"code",co.getCode().toArray());
    //    //wrapper.and(w->{
    //    //    w.like("org.name",co.getOrgName());
    //    //    w.like(wrapper.getAlias()+'.'+"name",co.getName());
    //    //});
    //    ////wrapper.or(w->w.eq(wrapper.getAlias()+'.'+"code", qo.getCode()).or(w1->w1.eq(wrapper.getAlias()+'.'+"code", qo.getCode())));
    //    ////wrapper.or().eq(wrapper.getAlias()+'.'+"code", qo.getCode()).or().eq(wrapper.getAlias()+'.'+"code", qo.getCode());
    //    //return this.service.getRepository().getMapper().selectJoinList(this.service.getRepository().getEntityClass(),wrapper);
    //}

    @GetMapping(path="/get/{id}")
    public DeptEntity getDept(@PathVariable String id){
        return this.service.get(id);
    }

}
