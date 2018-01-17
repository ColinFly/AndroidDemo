package com.yf.rxjava;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by colin on 18-1-11.
 */

public class MainMap {
    //打印每个学生选的课程，分别用Java和Rxjava实现
    static class Course {
        public Course(String name) {
            this.name = name;
        }

        private String name;
    }

    static class Student {
        private String name;
        private List<Course> courseList;

        public Student(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        //课程
        Course chinese = new Course("语文");
        Course english = new Course("英语");
        Course math = new Course("数学");
        Course computer = new Course("计算机");
        Course pe = new Course("体育");

        List<Course> c1 = new ArrayList<>();
        c1.add(chinese);
        c1.add(english);
        c1.add(math);

        //课程列表
        List<Course> c2 = new ArrayList<>();
        c2.add(english);
        c2.add(computer);
        c2.add(math);

        List<Course> c3 = new ArrayList<>();
        c3.add(math);
        c3.add(computer);
        c3.add(pe);


        //学生
        final Student s1 = new Student("小米");
        s1.courseList = c1;

        Student s2 = new Student("小明");
        s2.courseList = c2;

        Student s3 = new Student("校长");
        s3.courseList = c3;

        //学生列表
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);

        //一般的双层循环就可以解决这个问题了
        for (int i = 0; i < studentList.size(); i++) {
            String name = studentList.get(i).name;
            List<Course> courses = studentList.get(i).courseList;
            for (int j = 0; j < courses.size(); j++) {
                System.out.println(name + "所选课程: " + courses.get(j).name);
            }
        }

        //这里的flatMap也可以实现,但是在打印的时候学生的名字访问不到了
        Observable.from(studentList)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.courseList);
                    }
                }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                System.out.println(course.name);
            }
        });

    }

}
