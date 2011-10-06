package spectacular.spec.execution;


import spectacular.data.model.Executable;

import java.util.LinkedList;
import java.util.List;

public class ExecutableSpec {

    private int id;
    private Executable specification;
    private List<Integer> downstreamSpecifications;
    private List<Integer> upstreamSpecifications;

    public ExecutableSpec(int id, Executable executable) {
        this.id = id;
        this.specification = executable;
        this.downstreamSpecifications = new LinkedList<Integer>();
        this.upstreamSpecifications = new LinkedList<Integer>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Executable getSpecification() {
        return specification;
    }

    public void setSpecification(Executable specification) {
        this.specification = specification;
    }

    public List<Integer> getDownstreamSpecifications() {
        return downstreamSpecifications;
    }

    public void setDownstreamSpecifications(List<Integer> downstreamSpecifications) {
        this.downstreamSpecifications = downstreamSpecifications;
    }

    public void addDownstreamSpecification(Integer spec) {
        this.downstreamSpecifications.add(spec);
    }

    public List<Integer> getUpstreamSpecifications() {
        return upstreamSpecifications;
    }

    public void setUpstreamSpecifications(List<Integer> upstreamSpecifications) {
        this.upstreamSpecifications = upstreamSpecifications;
    }

    public void addUpstreamSpecification(Integer spec) {
        this.upstreamSpecifications.add(spec);
    }
}
