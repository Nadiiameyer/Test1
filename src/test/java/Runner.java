import org.junit.platform.suite.api.*;
import ui.GitHubTest;

@SelectPackages("ui")
@SelectClasses(GitHubTest.class)
@IncludeTags("regression")
@ExcludeTags("omit")
@Suite
public class Runner {
}
