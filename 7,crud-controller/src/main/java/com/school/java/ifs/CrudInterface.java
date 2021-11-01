package com.school.java.ifs;

import com.school.java.model.network.Header;

public interface CrudInterface {

    Header create();    // todo request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
