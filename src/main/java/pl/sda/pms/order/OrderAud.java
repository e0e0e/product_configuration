package pl.sda.pms.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAud {

    private List<FeatureAud> featureAuds;
    private Date date;
    private Long revision;
    private Ord order;

    public OrderAud() {
    }

    public Ord getOrder() {
        return order;
    }

    public void setOrder(Ord order) {
        this.order = order;
    }

    public List<FeatureAud> getFeatureAuds() {
        return featureAuds;
    }

    public void setFeatureAuds(List<FeatureAud> featureAuds) {
        this.featureAuds = featureAuds;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getRevision() {
        return this.revision;
    }

    public void setRevision(Long revision) {
        this.revision = revision;
    }

    public void addFeatureAud(FeatureAud featureAud) {
        if(this.featureAuds==null){
            List<FeatureAud> featureAuds = new ArrayList<>();
            featureAuds.add(featureAud);
            this.featureAuds=featureAuds;

        }else{
            this.featureAuds.add(featureAud);
        }


	}

}