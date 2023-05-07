package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 데이터베이스 트랜잭션 시작

        try {
//            // <회원 정보 생성>
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("hong");
//
//            em.persist(member);

            // <회원 정보 찾기>
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());

            // <회원 정보 수정>
            findMember.setName("hohoho");

            // 여기에서 em.persist()로 저장할 필요 없다. 변경 발생 시 JPA에서 트랜잭션 커밋 시점에 체크해서 바뀌면 업데이트 쿼리를 날려서 수정한다.

            tx.commit(); // 커밋
        } catch (Exception e) {
            tx.rollback(); // 롤백
        }finally {
            em.close();
        }
        emf.close();
    }
}
