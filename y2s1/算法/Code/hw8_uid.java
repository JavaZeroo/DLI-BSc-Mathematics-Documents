import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

public class hw8_uid {
    public static void main(String[] args) {
        System.out.println("------------Student info------------");
        DataCenter dc = new DataCenter();
        Course math = new Course("math", 3);
        Course algebra = new Course("algebra", 4);
        Course english = new Course("english", 2);
        Student hanmei = new Student(10001, "HanMei", "Math", 'f');
        hanmei.addCourse(math, 78);
        hanmei.addCourse(algebra, 66);
        hanmei.addCourse(english, 98);
        System.out.println(hanmei.toString());
        Student python = new Student(10002, "Python", "Math", 'm');
        python.addCourse(math, 90);
        python.addCourse(algebra, 99);
        python.addCourse(english, 80);
        System.out.println(python.toString());
        Student cpp = new Student(10003, "Cpp", "Code", 'm');
        cpp.addCourse(math, 97);
        cpp.addCourse(algebra, 65);
        cpp.addCourse(english, 70);
        System.out.println(cpp.toString());
        Student jimmy = new Student(10004, "Jimmy", "Math", 'm');
        jimmy.addCourse(math, 97);
        jimmy.addCourse(algebra, 99);
        jimmy.addCourse(english, 98);
        System.out.println(jimmy.toString());

        System.out.println("-----------------test---------------");
        dc.addStd(hanmei);
        dc.addStd(python);
        dc.addStd(cpp);
        dc.addStd(jimmy);

        System.out.println("Find the unique points for math\n" + dc.uniquePointsForCourse("math").toString());

        System.out.println("\nFind the student whose ID is 10004: \n" + dc.getStudent(10004).getName());

        System.out.println("\nFind the distribution of points for algebra");
        HashMap<Double, Integer> pointDis = dc.pointDis("algebra");
        for (Double i : pointDis.keySet()) {
            System.out.println("Point: " + i + " : " + pointDis.get(i));
        }

        System.out.println("\nFind the distribution of grades for the student whose id is 10001");
        HashMap<Character, Integer> gradeDis = dc.gradeDis(10001);
        for (char i : gradeDis.keySet()) {
            System.out.println("Grade: " + i + " : " + gradeDis.get(i));
        }
    }

}

class Student {
    private int id;
    private String name, major;
    private char gender;
    private ArrayList<Course> courselist = new ArrayList<Course>();

    public Student(int id, String name, String major, char gender) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.gender = gender;
    }

    public void addCourse(Course course) {
        courselist.add(course);
    }

    public void addCourse(Course course, double point) {
        Course c = new Course(course.getcName(), course.getCredit(), point);
        // course.setPoint(point);
        courselist.add(c);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courselist;
    }

    public String toString() {
        String ret = "Student: " + name + "\nID: " + id + ", Major: " + major + ", Gender: " + gender
                + "\nCourse list:\n";
        for (Course c : courselist) {
            ret += '[' + c.toString() + "]\n";
        }
        return ret;
    }
}

class DataCenter {
    private ArrayList<Student> stus = new ArrayList<Student>();
    private HashMap<Integer, Student> index = new HashMap<>();

    public void addStd(Student student) {
        stus.add(student);
        index.put(student.getId(), student);
    }

    public Student getStudent(int id) {
        return index.get(id);
    }

    public HashMap<Character, Integer> gradeDis(int id) {
        HashMap<Character, Integer> ret = new HashMap<>();
        ret.put('A', 0);
        ret.put('B', 0);
        ret.put('C', 0);
        ret.put('D', 0);
        ret.put('E', 0);
        Student stu = getStudent(id);
        for (Course c : stu.getCourses()) {
            double point = c.getPoint();
            if (point >= 90) {
                ret.replace('A', ret.get('A') + 1);
            } else if (point >= 80) {
                ret.replace('B', ret.get('B') + 1);
            } else if (point >= 70) {
                ret.replace('C', ret.get('C') + 1);
            } else if (point >= 60) {
                ret.replace('D', ret.get('D') + 1);
            } else {
                ret.replace('E', ret.get('E') + 1);
            }
        }
        return ret;
    }

    HashMap<Double, Integer> pointDis(String name) {
        HashMap<Double, Integer> ret = new HashMap<>();
        for (Student s : stus) {
            //use indoexOf
            ArrayList<Course> cList = s.getCourses();
            int index = cList.indexOf(new Course(name, 0));
            if (index == -1) {
                continue;
            } else {
                Course course = cList.get(index);
                double point = course.getPoint();
                if (!ret.containsKey(point)) {
                    ret.put(point, 1);
                } else {
                    ret.replace(point, ret.get(point) + 1);
                }
            }
            // for (Course c : s.getCourses()) {
            // if (c.getcName() != name) {
            // continue;
            // }
            // double point = c.getPoint();
            // if (!ret.containsKey(point)) {
            // ret.put(point, 1);
            // } else {
            // ret.replace(point, ret.get(point) + 1);
            // }
            // }
        }
        return ret;
    }

    List<Double> uniquePointsForCourse(String name) {
        Set<Double> ret = new TreeSet<>();
        for (Student s : stus) {
            for (Course c : s.getCourses()) {
                if (c.getcName() == name) {
                    ret.add(c.getPoint());
                }
            }
        }
        List<Double> ans = new ArrayList<>();
        ans.addAll(ret);
        return ans;
    }
}

class Course {
    private String cName;
    private double credit;
    private double point;

    public Course(String cName, double credit) {
        this.cName = cName;
        this.credit = credit;
    }

    public Course(String cName, double credit, double point) {
        this.cName = cName;
        this.credit = credit;
        this.point = point;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String toString() {
        return "Course name: " + cName + ", Credit: " + credit + ", Point: " + point;
    }

    @Override
    public boolean equals(Object o) {
        Course t = (Course) o;

        if (this.cName == t.cName)
            return true;
        else
            return false;
    }
}