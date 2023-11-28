package A1B1O3.bodyrecord.report.domain.repository;

import A1B1O3.bodyrecord.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static A1B1O3.bodyrecord.common.type.StatusType.USEABLE;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PUBLIC;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PUBLIC)
@SQLDelete(sql = "UPDATE report_category SET state = 'DELETED' WHERE reportcategory_code = ?")
@Where(clause = "state = 'USEABLE'")
public class ReportCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int reportcategoryCode;

    @Column
    private String reportcategoryName;

    public ReportCategory(final int reportcategoryCode,
                     final String reportcategoryName
    ) {
        super(USEABLE);
        this.reportcategoryCode = reportcategoryCode;
        this.reportcategoryName = reportcategoryName;
    }
}
