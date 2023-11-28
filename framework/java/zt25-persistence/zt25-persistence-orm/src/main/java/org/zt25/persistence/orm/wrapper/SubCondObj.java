package org.zt25.persistence.orm.wrapper;

import lombok.Data;
import org.zt25.persistence.orm.annotation.SubCond;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubCondObj {

    private SubCond subCond;
    private List<Field> fields;

    private List<SubCondObj> childSubCondObjs=new ArrayList<>();
}
