import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;


public class BowlingGameTest {

    private PlayerScore playerScore(int... rolls){
        PlayerScore playerScore = new PlayerScore("Luis");
        stream(rolls).forEach(pins -> playerScore.roll(pins));
        return playerScore;
    }

    @Before
    public void setUp() throws Exception{

    }
    
    @Test
    public void given_no_rolls_frames_should_be_zero() {
        assertThat(playerScore().frames().size()).isZero();
    }

    @Test
    public void given_one_roll_frames_should_be_one_and_score_is_null() {
        assertThat(playerScore(0).frames().size()).isEqualTo(1);
        assertThat(playerScore(0).frame(0).score()).isNull();
    }

    @Test
    public void given_two_rolls_frames_should_be_one_and_score_is_sum_of_rolls() {
        assertThat(playerScore(0, 2).frames().size()).isEqualTo(1);
        assertThat(playerScore(0, 2).frame(0).score()).isEqualTo(2);
    }

    @Test
    public void given_three_rolls_frames_should_be_one_and_score_is_sum_of_rolls() {
        assertThat(playerScore(0, 2, 7).frames().size()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7).frame(0).score()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7).frame(1).score()).isNull();
    }

    @Test
    public void given_a_spare_in_the_last_frame_score_should_be_null() {
        assertThat(playerScore(0, 2, 7, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3).frame(0).score()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3).frame(1).score()).isNull();
    }

    @Test
    public void given_a_spare_in_a_frame_score_should_be_the_following_roll() {
        assertThat(playerScore(0, 2, 7, 3, 8).frames().size()).isEqualTo(3);
        assertThat(playerScore(0, 2, 7, 3, 8).frame(0).score()).isEqualTo(2);
        assertThat(playerScore(0, 2, 7, 3, 8).frame(1).score()).isEqualTo(18);
        assertThat(playerScore(0, 2, 7, 3, 8).frame(2).score()).isNull();
    }

    @Test
    public void given_a_strike_followed_by_roll_frame_should_be_null() {
        assertThat(playerScore(10, 3).frames().size()).isEqualTo(2);
        assertThat(playerScore(10, 3).frame(0).score()).isNull();
        assertThat(playerScore(10, 3).frame(1).score()).isNull();
    }

    private class PlayerScore {
        private List<Integer> rolls;
        private String name;

        public PlayerScore(String name) {
            this.name = name;
            this.rolls = new ArrayList<>();
        }

        public List<Frame> frames() {
            ArrayList<Frame> frames = new ArrayList<>();
            for (int i = 0; i < rolls.size();) {
                Frame frame = new Frame(i);
                frames.add(frame);
                i += frame.isStrike() ? 1 : 2;
            }
            return frames;
        }

        public PlayerScore roll(int pins) {
            rolls.add(pins);
            return this;
        }

        public Frame frame(int i) {
            return frames().get(i);
        }

        private class Frame {

            private int index;

            public Frame(int index) {
                this.index = index;
            }

            public Integer score() {
                if (!isTerminated()) return null;
                if (isSpare()) return roll(index) + roll(index + 1) + roll(index + 2);
                return roll(index) + roll(index + 1);
            }

            private Integer roll(int index) {
                return rolls.get(index);
            }

            private boolean isTerminated() {
                return this.index != rolls.size() - rollsToTerminate();

            }

            private int rollsToTerminate() {
                return isSpare() || isStrike() ? 2 : 1;
            }

            private boolean isSpare() {
                if (index + 1 >= rolls.size()) return false;
                return roll(index) + roll(index + 1) == 10;

            }

            private boolean isStrike() {
                return roll(index) == 10;
            }

        }
    }
}
