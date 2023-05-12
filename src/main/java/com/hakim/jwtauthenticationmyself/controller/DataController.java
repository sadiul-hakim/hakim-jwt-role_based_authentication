package com.hakim.jwtauthenticationmyself.controller;

import com.hakim.jwtauthenticationmyself.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {
    private static List<Data> dataList=List.of(
            new Data("Secret Data 1",1),
            new Data("Secret Data 2",2),
            new Data("Secret Data 3",3),
            new Data("Secret Data 4",4)
    );

    @GetMapping("/list")
    public ResponseEntity<List<Data>> getDataList(){
        return ResponseEntity.ok(dataList);
    }

    @DeleteMapping("/{dataId}")
    public ResponseEntity<?> deleteData(@PathVariable long dataId){
        List<Data> newList=new ArrayList<>(dataList);
        Data data=newList.stream().filter(d->d.id()==dataId)
                .findFirst().orElseThrow(()->new ResourceNotFoundException("Data not found with id: "+dataId));
        newList.remove(data);
        dataList=Collections.unmodifiableList(newList);

        return ResponseEntity.ok("Date deleted successfully.");
    }
}

record Data(String data,long id){}
