package ime.model;

/**
 * The LevelAdjustCurveImpl class implements the LevelAdjustCurve interface and represents a
 * mathematical model for adjusting levels in an image. It calculates coefficients for a quadratic
 * equation based on black, mid, and white levels.
 */
class LevelAdjustCurveImpl implements LevelAdjustCurve {
  private final int black;
  private final int mid;
  private final int white;
  private double aCoefficient;
  private double bCoefficient;
  private double cCoefficient;

  /**
   * Constructs a new LevelAdjustCurveImpl object with the specified black, mid, and white levels.
   * It calculates the coefficients for the quadratic equation upon construction.
   *
   * @param black The black level.
   * @param mid   The mid level.
   * @param white The white level.
   */
  public LevelAdjustCurveImpl(int black, int mid, int white) {
    this.black = black;
    this.mid = mid;
    this.white = white;
    calculateCoefficients();
  }

  private void calculateCoefficients() {
    double a = (black * black * (mid - white)) - (black * (mid * mid - white * white))
            + (white * mid * mid) - (mid * white * white);
    double aa = (-black * (128 - 255)) + (128 * white) - (255 * mid);
    double ab = (black * black * (128 - 255)) + (255 * mid * mid) - (128 * white * white);
    double ac = (black * black * (255 * mid - 128 * white))
            - (black * ((255 * mid * mid) - (128 * white * white)));
    aCoefficient = aa / a;
    bCoefficient = ab / a;
    cCoefficient = ac / a;
  }

  @Override
  public double getACoefficient() {
    return aCoefficient;
  }

  @Override
  public double getBCoefficient() {
    return bCoefficient;
  }

  @Override
  public double getCCoefficient() {
    return cCoefficient;
  }
}
