package com.example.springmaven;

import com.example.springmaven.model.TypeUser;
import com.example.springmaven.model.User;
import com.example.springmaven.repository.ITypeUser;
import com.example.springmaven.repository.IUserRepository;
import com.example.springmaven.service.impl.TypeUserService;
import com.example.springmaven.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringMavenApplicationTests {

    @Test
    void contextLoads() {
    }

    @MockBean
    ITypeUser typeUser;

    @Autowired
    TypeUserService typeUserService;

    @Before
    public void setUp() {
        Mockito.when(typeUser.findAll()).thenReturn(IntStream.range(0,10)
                .mapToObj(i -> new TypeUser())
                .collect(Collectors.toList()));
    }

    @Test
    public void testCount() {
        Assert.assertEquals(10, typeUserService.findAllTypeUserCount());
    }

}
