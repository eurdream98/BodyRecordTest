package A1B1O3.bodyrecord.common;


import A1B1O3.bodyrecord.common.type.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static A1B1O3.bodyrecord.common.type.StatusType.DELETED;
import static A1B1O3.bodyrecord.common.type.StatusType.USEABLE;
import static javax.persistence.EnumType.STRING;

@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private StatusType state = USEABLE;

    protected BaseEntity(final StatusType state) {
        this.state = state;
    }

    public boolean isDeleted() {
        return this.state.equals(DELETED);
    }

    public void changeStatusToDeleted() {
        this.state = DELETED;
    }
}
