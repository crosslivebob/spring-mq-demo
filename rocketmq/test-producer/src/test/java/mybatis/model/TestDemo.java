package mybatis.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestDemo implements Serializable {
    private Long id;
    private Integer flag;
    private String name;
}
